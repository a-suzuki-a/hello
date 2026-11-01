INSERT INTO students
(name, kana, nickname, mailaddress, tiiki, age, gender, remark, isDeleted)
VALUES
    ('山田太郎', 'ヤマダタロウ', 'たろう', 'taro.yamada@example.com', '東京都', 25, '男性', '', 0),
    ('佐藤花子', 'サトウハナコ', 'はな', 'hanako.sato@example.com', '神奈川県', 28, '女性', '', 0),
    ('田中一郎', 'タナカイチロウ', 'いち', 'ichiro.tanaka@example.com', '大阪府', 32, '男性', '', 0),
    ('高橋美咲', 'タカハシミサキ', 'みさき', 'misaki.takahashi@example.com', '愛知県', 22, '女性', '', 0),
    ('伊藤健太', 'イトウケンタ', 'けんた', 'kenta.ito@example.com', '福岡県', 30, '男性', '', 0);

INSERT INTO students_courses
(student_id, course, start_date, schedule_end_date)
VALUES
    (1, 'Javaコース', '2026-10-01', '2027-09-30'),
    (2, 'AWSコース', '2026-10-01', '2027-09-30'),
    (3, 'Javaコース', '2026-10-10', '2027-10-09'),
    (4, 'Web開発コース', '2026-10-15', '2027-10-14'),
    (5, 'AWSコース', '2026-10-20', '2027-10-19');