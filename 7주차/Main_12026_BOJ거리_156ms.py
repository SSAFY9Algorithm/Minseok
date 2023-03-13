import sys
n = int(input())
arr = input()
visit = [0] + [sys.maxsize] * (n-1)
d = {'B' : 0, 'O' : 1, 'J' : 2}
for i in range(n):
    for j in range(i+1, n):
        if (d[arr[i]]+1)%3 == d[arr[j]]:
            visit[j] = min(visit[j], visit[i] + (j-i)**2)
print(visit[n-1] if visit[n-1] != sys.maxsize else -1)