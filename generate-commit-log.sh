#!/bin/bash 

# Archivo destino
OUTPUT_FILE="README.md"

# Cabecera de la tabla
echo "## 📜 Historial de Commits" > $OUTPUT_FILE
echo "" >> $OUTPUT_FILE
echo "| Fecha       | Hash     | Mensaje de Commit                         |" >> $OUTPUT_FILE
echo "|-------------|----------|-------------------------------------------|" >> $OUTPUT_FILE

# Extrae el log y lo formatea en filas Markdown
git log --pretty=format:"| %ad | \`%h\` | %s |" --date=short >> $OUTPUT_FILE

echo -e "\n✅ Historial generado correctamente en $OUTPUT_FILE"
