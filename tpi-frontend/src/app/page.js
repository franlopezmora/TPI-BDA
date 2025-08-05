import Link from 'next/link';

export default function HomePage() {
  return (
    <main className="min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center p-8">
      <h1 className="text-4xl font-bold mb-8">TPI Backend - Frontend</h1>

      <nav className="flex flex-col gap-4 w-full max-w-md">
        <Link
          href="/empleados"
          className="bg-gray-600 hover:bg-blue-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver empleados
        </Link>
        <Link
          href="/vehiculos"
          className="bg-gray-600 hover:bg-green-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver vehículos
        </Link>
        <Link
          href="/modelos"
          className="bg-gray-600 hover:bg-purple-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver modelos
        </Link>
        <Link
          href="/marcas"
          className="bg-gray-600 hover:bg-orange-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver marcas
        </Link>
        <Link 
          href="/interesados"
          className="bg-gray-600 hover:bg-yellow-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver interesados
        </Link>
        <Link
          href="/zonasPeligrosas"
          className="bg-gray-600 hover:bg-red-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver zonas peligrosas
        </Link>
        <Link
          href="/pruebas"
          className="bg-gray-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver pruebas
        </Link>
        <Link
          href="/posiciones"
          className="bg-gray-600 hover:bg-red-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300 text-center"
        >
          Ver posiciones
        </Link>
      </nav>
    </main>
  );
}
