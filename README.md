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



