import sys
input = sys.stdin.readline
n, m = map(int, input().split())
arr = [[0] * (m+1)] + [[0] + [*map(int, input().split())] for _ in range(n)]
ans = -10000

for i in range(1, n + 1):
    row = [0] * (m + 1)
    for j in range(i, n + 1):
        # row 체크하는 임시 배열
        temp = [0] * (m + 1)
        for k in range(1, m + 1):
            # 실제 값
            row[k] += arr[j][k]
            # 가능한 최대 값 누적합으로 구함
            temp[k] = max(temp[k - 1] + row[k], row[k])
            ans = max(temp[k], ans)

print(ans)