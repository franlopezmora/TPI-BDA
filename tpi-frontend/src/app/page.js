import Link from 'next/link';

export default function HomePage() {
  return (
    <main style={{ padding: '2rem' }}>
      <h1>TPI Backend Frontend</h1>
      <nav style={{ display: 'flex', flexDirection: 'column', gap: '1rem', marginTop: '2rem' }}>
        <Link href="/empleados">Ver empleados</Link>
        <Link href="/vehiculos">Ver vehículos</Link>
      </nav>
    </main>
  );
}