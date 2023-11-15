# Monitorización de microservicios Spring Boot con Grafana y Prometheus

Puedes ver las transparencias [aquí](https://docs.google.com/presentation/d/1cPpkaI9mPi5Wz9mH8XnjRrPqN9aa_OgAPW6slcztikU/edit?usp=sharing)

## Sistema de monitorización
### Peparar el entorno
#### Instalar Prometheus

```shell
$ helm install prometheus oci://registry-1.docker.io/bitnamicharts/prometheus -f monitoring-system/prometheus.values.yml
$ k port-forward svc/prometheus-server 9090:80
```

#### Instalar Grafana

```shell
$ helm install grafana oci://registry-1.docker.io/bitnamicharts/grafana -f monitoring-system/grafana.values.yml
$ kubectl port-forward svc/grafana 3000:3000
$ echo "Password: $(kubectl get secret grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d)"
```