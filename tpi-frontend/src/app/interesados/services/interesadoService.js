const API_URL = "http://localhost:8080/admin/interesados";

export async function getInteresados() {
  const res = await fetch(API_URL);
  if (!res.ok) throw new Error("Error al obtener interesados");
  return res.json();
}

export async function createInteresado(data) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error("Error al crear interesado");
}

export async function updateInteresado(id, data) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error("Error al actualizar interesado");
}

export async function deleteInteresado(id) {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error al eliminar interesado");
}
