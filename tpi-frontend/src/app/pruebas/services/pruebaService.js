const BASE = 'http://localhost:8080/pruebas/pruebas'

export const getPruebas = () =>
  fetch(BASE)
    .then(res => {
      if (!res.ok) throw new Error('Error al obtener pruebas')
      return res.json()
    })

export const createPrueba = (prueba) =>
  fetch(BASE, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(prueba),
  }).then(res => {
    if (!res.ok) throw new Error('Error al crear prueba')
    return res.json()
  })

export async function updatePrueba(id, data) {
  const res = await fetch(`${BASE}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) {
    const text = await res.text();
    throw new Error("Error al actualizar prueba: " + text);
  }
  return res.json();
}

export const deletePrueba = (id) =>
  fetch(`${BASE}/${id}`, {
    method: 'DELETE',
  }).then(res => {
    if (!res.ok) throw new Error('Error al eliminar prueba')
    // no JSON body
  })