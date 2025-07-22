'use client'; // porque vamos a usar hooks y fetch del lado del cliente
import { useEffect, useState } from 'react';

export default function EmpleadosPage() {
  const [empleados, setEmpleados] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/admin/empleados')
      .then((res) => res.json())
      .then((data) => {
        setEmpleados(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Error al cargar empleados:', err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Cargando empleados...</p>;

  return (
    <main style={{ padding: '2rem' }}>
      <h1>Lista de empleados</h1>
      <ul>
        {empleados.map((emp) => (
          <li key={emp.legajo}>
            {emp.nombre} {emp.apellido} – Tel: {emp.telefonoContacto}
          </li>
        ))}
      </ul>
    </main>
  );
}
