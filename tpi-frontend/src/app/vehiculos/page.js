'use client';
import { useEffect, useState } from 'react';

export default function VehiculosPage() {
  const [vehiculos, setVehiculos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/vehiculos/vehiculos')
      .then((res) => res.json())
      .then((data) => {
        setVehiculos(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Error al cargar vehículos:', err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Cargando vehículos...</p>;

  return (
    <main style={{ padding: '2rem' }}>
      <h1>Lista de vehículos</h1>
      <ul>
        {vehiculos.map((veh) => (
          <li key={veh.id}>
            {veh.patente} – Año {veh.anio}
          </li>
        ))}
      </ul>
    </main>
  );
}
