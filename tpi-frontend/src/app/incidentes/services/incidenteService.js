const API = "http://localhost:8080/pruebas/incidentes";

export async function getIncidentes() {
    const res = await fetch(API);
    if (!res.ok) throw new Error("Error al obtener incidentes");
    return res.json();
}

export async function createIncidente(incidente) {
    const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(incidente),
    });
    if (!res.ok) throw new Error("Error al crear incidente");
}

export async function updateIncidente(id, incidente) {
    const res = await fetch(`${API}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(incidente),
    });
    if (!res.ok) throw new Error("Error al actualizar incidente");
}

export async function deleteIncidente(id) {
    const res = await fetch(`${API}/${id}`, { method: "DELETE" });
    if (!res.ok) throw new Error("Error al eliminar incidente");
}
