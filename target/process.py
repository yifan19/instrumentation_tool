import csv
import statistics
averageTime = []
fd = open('result.txt', 'r')
csvreader = csv.reader(fd, delimiter=',', quotechar='|')
for nThread in range(1, 64 + 1):
    for row in csvreader:
        if len(row) > 0 and 'total_time' in row[0]:
            break


    data = [] 
    for j in range(10):
        r = next(csvreader)
        print(r)
        for l in range(nThread):
            data.append(int(r[l]))
    
    averageTime.append(statistics.mean(data)/10e6 * 1e6)
    print(len(data))

for i in averageTime:
    print (i)


