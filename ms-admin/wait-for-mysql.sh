#!/bin/sh

echo "⏳ Waiting for MySQL..."

until nc -z mysql 3306; do
  echo "MySQL not ready yet..."
  sleep 2
done

echo "✅ MySQL ready!"
exec java -jar /app/app.jar