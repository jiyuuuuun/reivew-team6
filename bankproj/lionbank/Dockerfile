# MySQL 이미지를 기반으로 설정
FROM mysql:8.3.0

# 초기화 스크립트 복사
COPY database/init/init.sql /docker-entrypoint-initdb.d/init.sql

# MySQL의 기본 설정을 유지
CMD ["mysqld"]
