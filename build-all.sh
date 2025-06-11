#!/bin/bash

ROOT_DIR=$(pwd)

echo "📦 Building tpi-admin-service..."
cd "$ROOT_DIR/tpi-admin-service"
"$ROOT_DIR/mvnw" clean package -DskipTests

echo "📦 Building tpi-vehiculos-service..."
cd "$ROOT_DIR/tpi-vehiculos-service"
"$ROOT_DIR/mvnw" clean package -DskipTests

echo "📦 Building tpi-reportes-service..."
cd "$ROOT_DIR/tpi-reportes-service"
"$ROOT_DIR/mvnw" clean package -DskipTests

echo "📦 Building tpi-gateway-service..."
cd "$ROOT_DIR/tpi-gateway-service"
"$ROOT_DIR/mvnw" clean package -DskipTests

echo "✅ All builds completed."
