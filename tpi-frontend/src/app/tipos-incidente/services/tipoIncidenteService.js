const API = "http://localhost:8080/pruebas/tipos-incidente";

export async function getTipoIncidentes() {
    const res = await fetch(API);
    if (!res.ok) throw new Error("Error al obtener tipos de incidente");
    return res.json();
}

export async function createTipoIncidente(tipo) {
    const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(tipo),
    });
    if (!res.ok) throw new Error("Error al crear tipo de incidente");
}

export async function updateTipoIncidente(id, tipo) {
    const res = await fetch(`${API}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(tipo),
    });
    if (!res.ok) throw new Error("Error al actualizar tipo de incidente");
}

export async function deleteTipoIncidente(id) {
    const res = await fetch(`${API}/${id}`, { method: "DELETE" });
    if (!res.ok) throw new Error("Error al eliminar tipo de incidente");
}
