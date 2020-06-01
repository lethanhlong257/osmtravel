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

### 23/05/2020 - Report
Chức năng search:
Gõ keyword vào khung search có id #search-map-key-word
chủ yếu tìm đường
Ví dụ: Trần Hưng Đạo hoặc chợ bén thành
kết quả: trên bản đồ hiện ra các điểm kết quả search.
Phía dưới bản đồ hiện ra list các địa diểm và toạ độ.
Tuỳ chỉnh lại list cho đẹp
Next step: Click vào địa điểm trên bản đồ (hoặc list địa diểm bên dưới) sẽ hiển thị trang chi tiết địa diểm.

### 1/06/2020 - Report
Khi bắt đâu app cần mất nhiều thời giam để app index way trước khi sử dụng. Khoảng hơn 5000 index. Mất khoản 3 phút

**Chức năng: Advanced Search**
Mô tả: Sử dụng Google Geocoding API (bản giới hạn cho developer testing sử dụng hạn chế 2500 request) để tìm địa diểm trên bản dồ chính xác hơn.

Nếu muốn tìm kiếm dường hoặc địa chỉ nhà. Thông tin cần có là 1 địa diểm cụ thể. có số nhà tên dường và thành phố.


Ví dụ ta có thể search:
- 5 nguyễn tri phương, hồ chí minh
- toà nhà bitexco
- Thảo cầm viên quận 1
- Bệnh viện từ dũ
- cong vien tao dan
- công viên đầm sen
- dinh doc lap

**Chức năng: Routing**

Mô tả: Nhập 2 địa diểm cụ thể vào ô From và To
Ví dụ: 

From: 10 Hàm Nghi, hồ chí minh

To: Công viên tao đàn

Sử dụng geocoding để xác định vị trí toạ dộ trên bản đồ. Sau đó dùng thuật toán dijstrak để xác định dường đi ngắn nhất và vẽ lên bản đồ


**Chức năng: Admin**
Đã sửa lại admin. trang sửa phần add new place

Note: Có rất nhiều chỗ cần sửa lại CSS cho đẹp như search button - và nhiều chỗ khác


