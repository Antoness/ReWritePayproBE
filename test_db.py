import psycopg2

try:
    conn = psycopg2.connect("dbname='payroll_db' user='postgres' host='localhost' password='dev@dika' port='5432'")
    cur = conn.cursor()
    query = """
    SELECT DISTINCT ms.id, ms.created_date, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, ms.employee_type AS Employee_Type, TO_CHAR(ms.created_date, 'DD/MM/YYYY') AS Created_Date, TO_CHAR(ms.update_date, 'DD/MM/YYYY') AS Update_Date, ms.created_by AS Created_By, ms.approval AS Status 
    FROM master_salary ms 
    LEFT JOIN users u ON u.id = ms.id_user 
    LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id 
    WHERE ((ms.id_user = 1 AND ms.approval = 'REQUEST') OR (mp."user" = 'Staff HRD' AND ms.approval = 'APPROVED') OR (ms.pic = 'Staff HRD' AND ms.approval = 'APPROVED')) AND (ms.approval != 'DONE')  ORDER BY ms.created_date DESC
    """
    cur.execute(query)
    rows = cur.fetchall()
    print(f"Success! {len(rows)} rows found.")
except Exception as e:
    print(f"Error: {e}")
