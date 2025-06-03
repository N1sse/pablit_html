#!/bin/bash 

# Archivo destino
OUTPUT_FILE="README.md"

# Cabecera de la tabla
echo "## 📜 Historial de Commits" > $OUTPUT_FILE
echo "" >> $OUTPUT_FILE
# --- MODIFICACIÓN AQUÍ: Añadimos 'Autor' en el encabezado ---
echo "| Fecha       | Hash       | Mensaje de Commit                         | Autor |" >> $OUTPUT_FILE
echo "|-------------|------------|-------------------------------------------|-------|" >> $OUTPUT_FILE # --- Y aquí, con una línea para el separador ---

# Extrae el log y lo formatea en filas Markdown
# --- MODIFICACIÓN AQUÍ: Añadimos '%an' para el autor en el formato ---
git log --pretty=format:"| %ad | \`%h\` | %s | %an |" --date=short >> $OUTPUT_FILE

echo -e "\n✅ Historial generado correctamente en $OUTPUT_FILE"