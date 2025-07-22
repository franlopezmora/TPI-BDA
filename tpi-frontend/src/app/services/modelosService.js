export async function getModelos() {
  const res = await fetch('http://localhost:8080/vehiculos/modelos');
  if (!res.ok) throw new Error('Error al obtener modelos');
  return await res.json();
}

export async function createModelo(data) {
  const res = await fetch('http://localhost:8080/vehiculos/modelos', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error('Error al crear modelo');
  return await res.json();
}