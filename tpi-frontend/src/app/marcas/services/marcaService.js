const API = "http://localhost:8080/vehiculos/marcas";

export async function getMarcas() {
  const res = await fetch(API);
  if (!res.ok) throw new Error("Error al obtener marcas");
  return res.json();
}

export async function createMarca(marca) {
  const res = await fetch(API, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(marca),
  });
  if (!res.ok) throw new Error("Error al crear marca");
  return res.json();
}

export async function updateMarca(id, marca) {
  const res = await fetch(`${API}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(marca),
  });
  if (!res.ok) throw new Error("Error al actualizar marca");
  return res.json();
}

export async function deleteMarca(id) {
  const res = await fetch(`${API}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error al eliminar marca");
}
