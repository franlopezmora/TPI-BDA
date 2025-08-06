#!/bin/bash
set -euo pipefail

# map de nombres cortos 👉 carpeta real
declare -A SERVICES=(
  [admin]=tpi-admin-service
  [vehiculos]=tpi-vehiculos-service
  [reportes]=tpi-reportes-service
  [gateway]=tpi-gateway-service
  [notificaciones]=tpi-notificaciones-service
  [pruebas]=tpi-pruebas-service
)

usage() {
  echo "Uso: $0 {admin|vehiculos|reportes|gateway|notificaciones|pruebas}"
  exit 1
}

[[ $# -eq 1 ]] || usage

KEY="$1"
DIR="${SERVICES[$KEY]:-}"

if [[ -z "$DIR" ]]; then
  echo "❌ Servicio desconocido: '$KEY'"
  usage
fi

if [[ ! -d "$DIR" ]]; then
  echo "❌ No existe la carpeta '$DIR'"
  exit 1
fi

echo "📦 Build de '$DIR'..."
(
  cd "$DIR"
  mvn clean package -DskipTests
)
echo "✅ '$DIR' compilado con éxito."
