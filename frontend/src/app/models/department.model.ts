/**
 * Modèle TypeScript (Interface) représentant une Direction du Ministère.
 * Correspond exactement au JSON renvoyé par le record Java "DepartmentDto".
 */
export interface Department {
  id: number;
  code: string;
  name: string;
  location: string;
  description: string;
}
