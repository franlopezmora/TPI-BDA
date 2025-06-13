#!/bin/bash
set -e

echo "📦 Building tpi-admin-service..."
cd "tpi-admin-service"
mvn clean package -DskipTests
cd ..

echo "📦 Building tpi-vehiculos-service..."
cd "tpi-vehiculos-service"
mvn clean package -DskipTests
cd ..

echo "📦 Building tpi-reportes-service..."
cd "tpi-reportes-service"
mvn clean package -DskipTests
cd ..

echo "📦 Building tpi-gateway-service..."
cd "tpi-gateway-service"
mvn clean package -DskipTests
cd ..

echo "✅ All builds completed."
