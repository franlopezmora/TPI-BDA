const BASE = 'http://localhost:8080/vehiculos/posiciones';

export const getPosiciones = () =>
  fetch(BASE)
    .then(res => {
      if (!res.ok) throw new Error('Error al obtener posiciones');
      return res.json();
    });

export const createPosicion = (posicion) =>
  fetch(BASE, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(posicion),
  }).then(res => {
    if (!res.ok) throw new Error('Error al crear posición');
    return res.json();
  });

export const deletePosicion = (id) =>
  fetch(`${BASE}/${id}`, { method: 'DELETE' })
    .then(res => {
      if (!res.ok) throw new Error('Error al eliminar posición');
    });
