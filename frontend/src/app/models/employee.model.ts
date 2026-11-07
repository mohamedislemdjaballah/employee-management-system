/**
 * Statuts administratifs possibles d'un agent.
 * Correspond à l'enum Java "EmployeeStatus".
 */
export type EmployeeStatus = 'ACTIVE' | 'ON_LEAVE' | 'INACTIVE';

/**
 * Modèle TypeScript correspondant au JSON reçu depuis GET /api/employees (EmployeeResponseDto).
 */
export interface Employee {
  id: number;
  matricule: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  jobTitle: string;
  salary: number;
  hireDate: string; // Format ISO YYYY-MM-DD
  status: EmployeeStatus;
  departmentId: number;
  departmentCode: string;
  departmentName: string;
}

/**
 * Modèle TypeScript envoyé vers POST /api/employees ou PUT /api/employees/{id} (EmployeeRequestDto).
 */
export interface EmployeeRequest {
  matricule: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  jobTitle: string;
  salary: number;
  hireDate: string;
  status: EmployeeStatus;
  departmentId: number;
}
