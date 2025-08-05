const BASE_URL = "http://localhost:8080/vehiculos/modelos";

export async function getModelos() {
  const res = await fetch(BASE_URL);
  if (!res.ok) throw new Error("Error al obtener modelos");
  return res.json();
}

export async function createModelo(data) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error("Error al crear modelo");
  return res.json();
}

export async function updateModelo(id, data) {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error("Error al actualizar modelo");
  return res.json();
}

export async function deleteModelo(id) {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
  if (!res.ok) throw new Error("Error al eliminar modelo");
  return res;
}
