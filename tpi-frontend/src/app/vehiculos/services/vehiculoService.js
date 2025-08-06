const API = "http://localhost:8080/vehiculos/vehiculos";

export async function getVehiculos() {
  const res = await fetch(API);
  if (!res.ok) throw new Error("Error al obtener vehículos");
  return res.json();
}

export async function createVehiculo(vehiculo) {
  const res = await fetch(API, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vehiculo),
  });
  if (!res.ok) throw new Error("Error al crear vehículo");
}

export async function updateVehiculo(id, vehiculo) {
  const res = await fetch(`${API}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vehiculo),
  });
  if (!res.ok) throw new Error("Error al actualizar vehículo");
}

export async function deleteVehiculo(id) {
  const res = await fetch(`${API}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error al eliminar vehículo");
}
