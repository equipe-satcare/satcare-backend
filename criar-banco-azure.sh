# Script para criar um banco de dados na Azure, lembre-se de mudar para suas variaveis :)

# Ips liberados para acessar o banco
allowed_ip_start=179.216.25.21
allowed_ip_end=179.216.25.21

# Variaveis de nome e acesso do servidor e banco
server_name=my-server
database_name=my-database
admin_user=admin
admin_password=pass@123

# Recupera a o nome do recurso do Learn
json_groups=$(az group list)
res_group=$(jq '.[0].name' <<< $json_groups)
res_group="${res_group%\"}"
res_group="${res_group#\"}"

echo "Criando Servidor"
az sql server create --name $server_name --resource-group $res_group --location "Brazil South" --admin-user $admin_user --admin-password $admin_password

echo "Configurando firewall..."
az sql server firewall-rule create --resource-group $res_group --server $server_name -n AllowYourIp --start-ip-address $allowed_ip_start --end-ip-address $allowed_ip_end

echo "Criando banco de dados SQL"
az sql db create --resource-group $res_group --server $server_name --name $database_name --edition GeneralPurpose --compute-model Serverless --family Gen5 --capacity 2 --zone-redundant false --backup-storage-redundancy "Local" --auto-pause-delay -1