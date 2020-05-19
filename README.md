# osmtravel

*Hiện tại chỉ mới làm api chưa có giao diện nên muốn test function thì dùng api dể test*
*Ví du: *


### Hướng dẫn run project
- Sử dụng inteliJ:
Open inteliJ > open project > chọn project > bấm nút tam giác màu xanh lá dể run

- Sử dụng command line: Mở commandline hoặc terminal > Vào root directory của project
run câu lệnh "mvn spring-boot:run"

### Một số API

- Tìm số nodes trong bán kính n KM:
**/api/v1/get/nodes/distance/{km}/{lat}/{lon}** 
(với km là khoảng cách tính bằng KM, lat và lon là toạ độ diểm gốc). 
Ví dụ: /api/v1/get/nodes/distance/2/10.748902/106.687569 - 
nghĩa là tìm các node trong bán kính 2km tại node (10.748902, 106.687569)


### 19/05/2020 - Process
API to import node to mogoDB from file resource/minimap.osm

Method GET http://localhost:8080/api/v1.0/import/node
Khi chạy API này thì sẽ tạo 1 collection Node trong mongodb chứa các node của map
Do chưa có cơ chế check exist node nên muốn import lại file cũ thì phải xoá collection Node trong mongoDB đi

tương tư các api khac import way, relation cũng vậy
GET http://localhost:8080/api/v1.0/import/way
GET http://localhost:8080/api/v1.0/import/relation

Thời gian import mất cũng khá lâu do số lượng node, way, relation khá nhiều hơn 100,000 rows

**API search**

GET http://localhost:8080/api/v1.0/search?name=cho ben thanh

trả về danh sách các địa diểm map với dạng JSON

_Hiện tại chỉ mới làm dạng API chưa có giao diện cho chức năng search_

Chức năng dự kiến: Sau khi input địa diểm vào khung search tại homepage bấm nút search thì sẽ gọi ajax để gọi api search và trả về danh sách các địa diểm dựa theo keyword
