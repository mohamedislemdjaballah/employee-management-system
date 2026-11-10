/**
 * Serveur statique minimal pour l'application Angular compilée.
 *
 * - Sert les fichiers générés dans dist/ems-frontend/browser
 * - Renvoie index.html pour les routes Angular (fallback SPA)
 * - Utilise uniquement les modules natifs de Node.js (aucune dépendance)
 */
const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');

const PORT = Number(process.env.PORT) || 4200;
const ROOT = path.join(__dirname, 'dist', 'ems-frontend', 'browser');

const MIME_TYPES = {
  '.html': 'text/html; charset=utf-8',
  '.js': 'text/javascript; charset=utf-8',
  '.mjs': 'text/javascript; charset=utf-8',
  '.css': 'text/css; charset=utf-8',
  '.json': 'application/json; charset=utf-8',
  '.ico': 'image/x-icon',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.gif': 'image/gif',
  '.svg': 'image/svg+xml',
  '.woff': 'font/woff',
  '.woff2': 'font/woff2',
  '.ttf': 'font/ttf',
  '.map': 'application/json; charset=utf-8',
};

function sendFile(res, filePath, statusCode = 200) {
  const extension = path.extname(filePath).toLowerCase();
  res.writeHead(statusCode, {
    'Content-Type': MIME_TYPES[extension] || 'application/octet-stream',
    'Cache-Control': extension === '.html' ? 'no-cache' : 'public, max-age=3600',
  });
  fs.createReadStream(filePath).pipe(res);
}

const server = http.createServer((req, res) => {
  const requestPath = decodeURIComponent((req.url || '/').split('?')[0]);
  const safePath = path.normalize(requestPath).replace(/^(\.\.[/\\])+/, '');
  const filePath = path.join(ROOT, safePath);

  if (!filePath.startsWith(ROOT)) {
    res.writeHead(403, { 'Content-Type': 'text/plain; charset=utf-8' });
    res.end('Forbidden');
    return;
  }

  fs.stat(filePath, (error, stats) => {
    if (!error && stats.isFile()) {
      sendFile(res, filePath);
      return;
    }

    if (!error && stats.isDirectory()) {
      const indexPath = path.join(filePath, 'index.html');
      if (fs.existsSync(indexPath)) {
        sendFile(res, indexPath);
        return;
      }
    }

    // Fallback Angular : route inconnue -> index.html
    const fallback = path.join(ROOT, 'index.html');
    fs.stat(fallback, (fallbackError) => {
      if (fallbackError) {
        res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
        res.end('Not Found');
        return;
      }
      sendFile(res, fallback);
    });
  });
});

server.listen(PORT, '0.0.0.0', () => {
  console.log(`EMS frontend disponible sur http://localhost:${PORT}`);
});
