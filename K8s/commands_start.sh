# Создание Secret
kubectl apply -f postgresdb-secret.yml
sleep 3
kubectl get secret


# Создание ConfigMap
kubectl apply -f postgresdb-configmap.yml
sleep 3
kubectl get configmap

# Создание развертывания postgresdb-deployment
kubectl apply -f postgresdb-deployment.yml
sleep 10
kubectl logs -l app=postgresdb
sleep 50
kubectl describe pod

# Создание развертывания spring-boot-deployment
kubectl apply -f spring-boot-deployment.yml
sleep 10
kubectl logs -l app=spring-boot
sleep 50
kubectl describe pod
sleep 10
minikube service spring-boot-service 
