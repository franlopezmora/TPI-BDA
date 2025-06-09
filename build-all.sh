#!/bin/bash

echo "📦 Building tpi-admin-service..."
cd tpi-admin-service && ./mvnw clean package -DskipTests && cd ..

echo "📦 Building tpi-vehiculos-service..."
cd tpi-vehiculos-service && ./mvnw clean package -DskipTests && cd ..

echo "📦 Building tpi-reportes-service..."
cd tpi-reportes-service && ./mvnw clean package -DskipTests && cd ..

echo "📦 Building tpi-gateway-service..."
cd tpi-gateway-service && ./mvnw clean package -DskipTests && cd ..

echo "✅ All builds completed."
