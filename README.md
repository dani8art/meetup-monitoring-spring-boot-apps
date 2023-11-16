# Monitoring Spring Boot applications with Grafana & Prometheus

You can find the slides [here](https://docs.google.com/presentation/d/1cPpkaI9mPi5Wz9mH8XnjRrPqN9aa_OgAPW6slcztikU/edit?usp=sharing)

## Requirements

* [Docker] to provide service integration dependencies ([Postgresql] & [Kafka]). 
* [Kubernetes] cluster to provide the monitoring system.
* [Helm] to manage the monitoring system charts ([Prometheus] & [Grafana]).
* Java 17

## Service integrations

```shell
docker-compose up -d
```

## Monitoring System
### Set up the environment
#### Prometheus

> Note: Make sure you have updated the IP of the target host in `monitoring-system/prometheus.values.yml`.

##### Deploy bitnami/prometheus chart

```shell
helm install prometheus oci://registry-1.docker.io/bitnamicharts/prometheus -f monitoring-system/prometheus.values.yml
```

##### Expose prometheus service to your host

```shell
kubeclt port-forward svc/prometheus-server 9090:80
```

#### Grafana

> Note: Make sure you have updated the IPs for prometheus-server and prometheus-alertmanager in `monitoring-system/grafana.values.yml`.

##### Deploy bitnami/grafana chart

```shell
helm install grafana oci://registry-1.docker.io/bitnamicharts/grafana -f monitoring-system/grafana.values.yml
```

##### Get the grafana admin password

```shell
echo "Password: $(kubectl get secret grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d)"
```

##### Expose grafana service to your host

```shell
kubectl port-forward svc/grafana 3000:3000
```

[Docker]: https://www.docker.com
[Postgresql]: https://github.com/bitnami/containers/blob/main/bitnami/postgresql/README.md
[Kafka]: https://github.com/bitnami/containers/blob/main/bitnami/kafka/README.md
[Kubernetes]: https://kubernetes.io
[Helm]: https://helm.sh
[Prometheus]: https://github.com/bitnami/charts/blob/main/bitnami/prometheus/README.md
[Grafana]: https://github.com/bitnami/charts/blob/main/bitnami/grafana/README.md