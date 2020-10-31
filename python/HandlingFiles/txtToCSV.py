with open('HandlingFiles/biblia-em-txt.txt', 'r') as file_txt:
    txt=file_txt.read()
    with open('HandlingFiles/biblia-em-csv.csv', 'w') as file_csv:
        file_csv.write(txt)