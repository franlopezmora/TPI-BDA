const API_URL = "http://localhost:8080/vehiculos/zonas-peligrosas";

export const getZonas = async () => {
  const res = await fetch(API_URL);
  if (!res.ok) throw new Error("Error al obtener zonas");
  return await res.json();
};

export const createZona = async (zona) => {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(zona),
  });
  if (!res.ok) throw new Error("Error al crear zona");
};

export const updateZona = async (id, zona) => {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(zona),
  });
  if (!res.ok) throw new Error("Error al actualizar zona");
};

export const deleteZona = async (id) => {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error al eliminar zona");
};
