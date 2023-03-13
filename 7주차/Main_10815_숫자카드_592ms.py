n = int(input())
arr = set(map(int, input().split()))
m = int(input())
print(*[int(x in arr) for x in list(map(int, input().split()))])