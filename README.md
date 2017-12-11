# Workshop :: Legacy code with Java


## ขั้นตอนการใช้งาน

#### 1. ทำการ start Spring boot server

```
$mvn spring-boot:run
```

#### 2. ใช้งานผ่าน url=http://localhost:8080/ ใน Browser


#### 3. ตรวจสอบค่าของ Code coverage

```
$mvn clean cobertura:cobertura
```

## สิ่งที่ต้องทำคือ การเขียน Tests ในทุก ๆ ส่วน

* เป้าหมายคือ Code coverage = 100%
* Unit tests
* Integration tests
* API tests
* Acceptance tests
* GUI tests
