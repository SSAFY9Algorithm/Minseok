import sys
input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n-1)]
k = int(input())
dp = [[sys.maxsize for _ in range(2)] for _ in range(n)]
dp[0][0] = 0
if n > 1:
    dp[1][0] = arr[0][0]
if n > 2:
    dp[2][0] = min(arr[0][0] + arr[1][0], arr[0][1])

for i in range(3, n):
    # 매우 큰 점프 X
    dp[i][0] = min(dp[i-1][0] + arr[i-1][0], dp[i-2][0] + arr[i-2][1])
    # 매우 큰 점프 확인용
    dp[i][1] = min(min(dp[i-1][1] + arr[i-1][0], dp[i-2][1] + arr[i-2][1]), dp[i-3][0] + k)
print(min(dp[n-1][0], dp[n-1][1]))
