import sys
input = sys.stdin.readline

for _ in range(int(input())):
    # 아스키코드로 변환해서 -97 저장 => a:=0
    w = [*map(lambda x: ord(x) - 97, input().strip())]
    k, Min, Max = int(input()), len(w), -1

    arr = [[] for _ in range(26)]
    for i, v in enumerate(w):
        # 알파벳 값에 index 저장
        arr[v].append(i)

    for x in arr:
        for i in range(len(x) - k + 1):
            # 같은 글자로 가능한 길이의 최소값, 최대값 구함
            Min = min(Min, x[i+k-1] - x[i] + 1)
            Max = max(Max, x[i+k-1] - x[i] + 1)
    print(-1 if Max == -1 else f"{Min} {Max}")