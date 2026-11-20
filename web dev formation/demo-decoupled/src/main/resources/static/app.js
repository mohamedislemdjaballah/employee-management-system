// Tables
const employeeTable   = document.getElementById('employeeTable');
const departmentTable = document.getElementById('departmentTable');

// Buttons
const refreshBtn    = document.getElementById('refreshBtn');
const createEmpBtn  = document.getElementById('createEmpBtn');
const deleteEmpBtn  = document.getElementById('deleteEmpBtn');
const createDeptBtn = document.getElementById('createDeptBtn');
const deleteDeptBtn = document.getElementById('deleteDeptBtn');

// --- LOAD DATA (GET) ---

async function loadEmployees() {
  const response = await fetch('/api/employees', {
    method: 'GET'
  });
  const employees = await response.json();

  employeeTable.innerHTML = '';
  for (const emp of employees) {
    let deptName = '-';
    if (emp.department !== null) {
      deptName = emp.department.name;
    }

    employeeTable.innerHTML = employeeTable.innerHTML +
      '<tr>' +
        '<td>' + emp.id + '</td>' +
        '<td>' + emp.name + '</td>' +
        '<td>' + emp.lastName1 + '</td>' +
        '<td>' + emp.role + '</td>' +
        '<td>' + deptName + '</td>' +
      '</tr>';
  }
}

async function loadDepartments() {
  const response = await fetch('/api/departments', {
    method: 'GET'
  });
  const departments = await response.json();

  departmentTable.innerHTML = '';
  for (const dept of departments) {
    departmentTable.innerHTML = departmentTable.innerHTML +
      '<tr>' +
        '<td>' + dept.id + '</td>' +
        '<td>' + dept.name + '</td>' +
        '<td>' + dept.location + '</td>' +
      '</tr>';
  }
}

function refreshAll() {
  loadEmployees();
  loadDepartments();
}

// --- CREATE ROWS (POST) ---

async function createEmployee() {
  const nameInput     = document.getElementById('empName');
  const lastNameInput = document.getElementById('empLastName');
  const roleInput     = document.getElementById('empRole');
  const deptIdInput   = document.getElementById('empDeptId');

  let deptObj = null;
  if (deptIdInput.value !== '') {
    deptObj = { id: Number(deptIdInput.value) };
  }

  await fetch('/api/employees', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      name: nameInput.value,
      lastName1: lastNameInput.value,
      role: roleInput.value,
      department: deptObj
    })
  });

  nameInput.value = '';
  lastNameInput.value = '';
  roleInput.value = '';
  deptIdInput.value = '';
  loadEmployees();
}

async function createDepartment() {
  const nameInput     = document.getElementById('deptName');
  const locationInput = document.getElementById('deptLocation');

  await fetch('/api/departments', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      name: nameInput.value,
      location: locationInput.value
    })
  });

  nameInput.value = '';
  locationInput.value = '';
  loadDepartments();
}

// --- DELETE ROWS (DELETE) ---

async function deleteEmployee() {
  const idInput = document.getElementById('deleteEmpId');
  if (idInput.value === '') {
    return;
  }

  await fetch('/api/employees/' + idInput.value, {
    method: 'DELETE'
  });

  idInput.value = '';
  loadEmployees();
}

async function deleteDepartment() {
  const idInput = document.getElementById('deleteDeptId');
  if (idInput.value === '') {
    return;
  }

  await fetch('/api/departments/' + idInput.value, {
    method: 'DELETE'
  });

  idInput.value = '';
  loadDepartments();
}

// --- EVENT LISTENERS ---

refreshBtn.addEventListener('click', refreshAll);
createEmpBtn.addEventListener('click', createEmployee);
deleteEmpBtn.addEventListener('click', deleteEmployee);
createDeptBtn.addEventListener('click', createDepartment);
deleteDeptBtn.addEventListener('click', deleteDepartment);
