import Link from 'next/link';

export default function HomePage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-900 via-blue-900 to-gray-900">
      {/* Hero Section */}
      <section className="relative overflow-hidden">
        <div className="absolute inset-0 bg-black/20"></div>
        <div className="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-24">
          <div className="text-center">
            <h1 className="text-5xl md:text-7xl font-bold text-white mb-6">
              TPI Backend
            </h1>
            <p className="text-xl md:text-2xl text-blue-200 mb-8 max-w-3xl mx-auto">
              Sistema de gestión de pruebas de manejo construido con arquitectura de microservicios
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link
                href="#servicios"
                className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-4 px-8 rounded-lg shadow-lg transition duration-300 text-lg"
              >
                Explorar Servicios
              </Link>
              <Link
                href="#tecnologias"
                className="bg-transparent border-2 border-blue-400 text-blue-400 hover:bg-blue-400 hover:text-white font-semibold py-4 px-8 rounded-lg transition duration-300 text-lg"
              >
                Ver Tecnologías
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Microservicios Section */}
      <section id="servicios" className="py-20 bg-gray-800/50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-white mb-4">Arquitectura de Microservicios</h2>
            <p className="text-xl text-gray-300 max-w-3xl mx-auto">
              Sistema distribuido con 6 microservicios especializados, cada uno con su responsabilidad específica
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {/* Admin Service */}
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:border-blue-400 transition duration-300">
              <div className="text-blue-400 text-2xl mb-4">👨‍💼</div>
              <h3 className="text-xl font-semibold text-white mb-2">Admin Service</h3>
              <p className="text-gray-300 mb-4">Gestión de empleados, interesados, pruebas y zonas peligrosas</p>
              <div className="flex justify-between items-center">
                <span className="text-sm text-blue-400">Puerto 8081</span>
                <Link
                  href="/empleados"
                  className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg text-sm transition duration-300"
                >
                  Acceder
                </Link>
              </div>
            </div>

            {/* Vehículos Service */}
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:border-green-400 transition duration-300">
              <div className="text-green-400 text-2xl mb-4">🚗</div>
              <h3 className="text-xl font-semibold text-white mb-2">Vehículos Service</h3>
              <p className="text-gray-300 mb-4">Administración de vehículos, marcas y modelos</p>
              <div className="flex justify-between items-center">
                <span className="text-sm text-green-400">Puerto 8082</span>
                <Link
                  href="/vehiculos"
                  className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg text-sm transition duration-300"
                >
                  Acceder
                </Link>
              </div>
            </div>

            {/* Reportes Service */}
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:border-purple-400 transition duration-300">
              <div className="text-purple-400 text-2xl mb-4">📊</div>
              <h3 className="text-xl font-semibold text-white mb-2">Reportes Service</h3>
              <p className="text-gray-300 mb-4">Generación de reportes y estadísticas del sistema</p>
              <div className="flex justify-between items-center">
                <span className="text-sm text-purple-400">Puerto 8083</span>
                <Link
                  href="/reportes"
                  className="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded-lg text-sm transition duration-300"
                >
                  Acceder
                </Link>
              </div>
            </div>

            {/* Pruebas Service */}
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:border-indigo-400 transition duration-300">
              <div className="text-indigo-400 text-2xl mb-4">📝</div>
              <h3 className="text-xl font-semibold text-white mb-2">Pruebas Service</h3>
              <p className="text-gray-300 mb-4">Gestión específica de pruebas de manejo</p>
              <div className="flex justify-between items-center">
                <span className="text-sm text-indigo-400">Puerto 8085</span>
                <Link
                  href="/pruebas"
                  className="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg text-sm transition duration-300"
                >
                  Acceder
                </Link>
              </div>
            </div>

            {/* Notificaciones Service */}
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:border-yellow-400 transition duration-300">
              <div className="text-yellow-400 text-2xl mb-4">🔔</div>
              <h3 className="text-xl font-semibold text-white mb-2">Notificaciones Service</h3>
              <p className="text-gray-300 mb-4">Sistema de notificaciones via Discord webhook</p>
              <div className="flex justify-between items-center">
                <span className="text-sm text-yellow-400">Puerto 8084</span>
                <Link
                  href="/notificaciones"
                  className="bg-yellow-600 hover:bg-yellow-700 text-white px-4 py-2 rounded-lg text-sm transition duration-300"
                >
                  Acceder
                </Link>
              </div>
            </div>

            {/* Gateway Service */}
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:border-red-400 transition duration-300">
              <div className="text-red-400 text-2xl mb-4">🌐</div>
              <h3 className="text-xl font-semibold text-white mb-2">API Gateway</h3>
              <p className="text-gray-300 mb-4">Punto de entrada único y enrutamiento centralizado</p>
              <div className="flex justify-between items-center">
                <span className="text-sm text-red-400">Puerto 8080</span>
                <Link
                  href="/gateway"
                  className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg text-sm transition duration-300"
                >
                  Acceder
                </Link>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Tecnologías Section */}
      <section id="tecnologias" className="py-20 bg-gray-900/50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-white mb-4">Stack Tecnológico</h2>
            <p className="text-xl text-gray-300 max-w-3xl mx-auto">
              Tecnologías modernas y robustas para un sistema escalable y mantenible
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {/* Backend */}
            <div className="text-center">
              <h3 className="text-2xl font-semibold text-white mb-6">Backend</h3>
              <div className="space-y-4">
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-orange-400 font-semibold">Java 21</div>
                  <div className="text-gray-300 text-sm">Lenguaje principal</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-green-400 font-semibold">Spring Boot 3.3.5</div>
                  <div className="text-gray-300 text-sm">Framework de desarrollo</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-blue-400 font-semibold">PostgreSQL</div>
                  <div className="text-gray-300 text-sm">Base de datos principal</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-purple-400 font-semibold">Spring Cloud Gateway</div>
                  <div className="text-gray-300 text-sm">API Gateway</div>
                </div>
              </div>
            </div>

            {/* Frontend */}
            <div className="text-center">
              <h3 className="text-2xl font-semibold text-white mb-6">Frontend</h3>
              <div className="space-y-4">
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-blue-400 font-semibold">Next.js 15.4.3</div>
                  <div className="text-gray-300 text-sm">Framework React</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-cyan-400 font-semibold">React 19.1.0</div>
                  <div className="text-gray-300 text-sm">Biblioteca de UI</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-teal-400 font-semibold">Tailwind CSS 4</div>
                  <div className="text-gray-300 text-sm">Framework CSS</div>
                </div>
              </div>
            </div>

            {/* DevOps */}
            <div className="text-center">
              <h3 className="text-2xl font-semibold text-white mb-6">DevOps</h3>
              <div className="space-y-4">
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-blue-400 font-semibold">Docker</div>
                  <div className="text-gray-300 text-sm">Contenedores</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-blue-400 font-semibold">Docker Compose</div>
                  <div className="text-gray-300 text-sm">Orquestación</div>
                </div>
                <div className="bg-white/10 rounded-lg p-4">
                  <div className="text-orange-400 font-semibold">Maven</div>
                  <div className="text-gray-300 text-sm">Gestión de dependencias</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Acceso Rápido Section */}
      <section className="py-20 bg-gray-800/30">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-white mb-4">Acceso Rápido</h2>
            <p className="text-xl text-gray-300 max-w-3xl mx-auto">
              Navega directamente a las secciones principales del sistema
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            <Link
              href="/empleados"
              className="bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">👥</div>
              <h3 className="text-lg font-semibold mb-2">Empleados</h3>
              <p className="text-blue-100 text-sm">Gestión de personal</p>
            </Link>

            <Link
              href="/vehiculos"
              className="bg-gradient-to-r from-green-600 to-green-700 hover:from-green-700 hover:to-green-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">🚗</div>
              <h3 className="text-lg font-semibold mb-2">Vehículos</h3>
              <p className="text-green-100 text-sm">Flota de vehículos</p>
            </Link>

            <Link
              href="/interesados"
              className="bg-gradient-to-r from-yellow-600 to-yellow-700 hover:from-yellow-700 hover:to-yellow-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">📋</div>
              <h3 className="text-lg font-semibold mb-2">Interesados</h3>
              <p className="text-yellow-100 text-sm">Candidatos a pruebas</p>
            </Link>

            <Link
              href="/pruebas"
              className="bg-gradient-to-r from-purple-600 to-purple-700 hover:from-purple-700 hover:to-purple-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">📝</div>
              <h3 className="text-lg font-semibold mb-2">Pruebas</h3>
              <p className="text-purple-100 text-sm">Gestión de evaluaciones</p>
            </Link>

            <Link
              href="/zonasPeligrosas"
              className="bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">⚠️</div>
              <h3 className="text-lg font-semibold mb-2">Zonas Peligrosas</h3>
              <p className="text-red-100 text-sm">Áreas de riesgo</p>
            </Link>

            <Link
              href="/incidentes"
              className="bg-gradient-to-r from-orange-600 to-orange-700 hover:from-orange-700 hover:to-orange-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">🚨</div>
              <h3 className="text-lg font-semibold mb-2">Incidentes</h3>
              <p className="text-orange-100 text-sm">Reportes de eventos</p>
            </Link>

            <Link
              href="/modelos"
              className="bg-gradient-to-r from-indigo-600 to-indigo-700 hover:from-indigo-700 hover:to-indigo-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">🏷️</div>
              <h3 className="text-lg font-semibold mb-2">Modelos</h3>
              <p className="text-indigo-100 text-sm">Catálogo de modelos</p>
            </Link>

            <Link
              href="/marcas"
              className="bg-gradient-to-r from-pink-600 to-pink-700 hover:from-pink-700 hover:to-pink-800 text-white p-6 rounded-xl shadow-lg transition duration-300 text-center group"
            >
              <div className="text-3xl mb-4">🏢</div>
              <h3 className="text-lg font-semibold mb-2">Marcas</h3>
              <p className="text-pink-100 text-sm">Fabricantes de vehículos</p>
            </Link>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-900 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h3 className="text-2xl font-bold text-white mb-4">TPI Backend</h3>
            <p className="text-gray-400 mb-6">
              Sistema de gestión de pruebas de manejo - UTN FRC
            </p>
            <div className="flex justify-center space-x-6 text-gray-400">
              <span>Java 21</span>
              <span>•</span>
              <span>Spring Boot</span>
              <span>•</span>
              <span>Next.js</span>
              <span>•</span>
              <span>PostgreSQL</span>
            </div>
            <div className="mt-6 text-sm text-gray-500">
              <p>Desarrollado por Nicolás Garay, Mariano Iturriza, Marcos Belli y Francisco López Mora</p>
              <p className="mt-2">Backend de Aplicaciones 2025 - Universidad Tecnológica Nacional</p>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
}
