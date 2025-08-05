// src/app/empleados/services/empleadoService.js
const BASE_URL = "http://localhost:8080/admin/empleados";

export async function getEmpleados() {
  const res = await fetch(BASE_URL);
  if (!res.ok) throw new Error("Error al obtener empleados");
  return res.json();
}

export async function createEmpleado(empleado) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(empleado),
  });
  if (!res.ok) throw new Error("Error al crear empleado");
  return res.json();
}

export async function updateEmpleado(id, empleado) {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(empleado),
  });
  if (!res.ok) throw new Error("Error al actualizar empleado");
  return res.json();
}

export async function deleteEmpleado(id) {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
  if (!res.ok) throw new Error("Error al eliminar empleado");
}
