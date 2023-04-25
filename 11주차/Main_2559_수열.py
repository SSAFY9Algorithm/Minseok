n, k = map(int, input().split())
arr = [*map(int, input().split())]
s = sum(arr[:k])
answer = s
# 슬라이딩 윈도우
for i in range(k, n):
    s += arr[i] - arr[i-k]
    answer = max(answer, s)
print(answer)
