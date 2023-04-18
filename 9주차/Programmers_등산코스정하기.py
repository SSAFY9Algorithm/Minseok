import sys
sys.setrecursionlimit(100000)
answer = [-1, 10000001]
arr, gates, summits, visit = [], [], [], []

def tracking(now, intensity):
    global answer
    # 목적지 도착
    if now in summits:
        if intensity < answer[1]:
            answer = [now, intensity]
        elif intensity == answer[1]:
            answer[0] = min(answer[0], now)
        return
    for next, cost in arr[now]:
        # 최대값으로 갱신
        inten = max(intensity, cost)
        # visit보다 작고 gate가 아니면 go
        if visit[next] > inten and next not in gates and inten <= answer[1]:
                visit[next] = inten
                tracking(next, inten)


def solution(n, p, g, s):
    global arr, paths, gates, summits, visit
    # 인접 리스트
    arr = [[] for _ in range(n+1)]
    # 짧은 경로 먼저
    paths = sorted(p, key=lambda x:x[1])
    # O(1)로 gate인지, summit인지 확인하기 위함
    gates = set(g)
    summits = set(s)
    visit = [10000001] * (n+1)
    for start, end, cost in paths:
        # 시작점 끝점 서로 추가
        arr[start].append([end, cost])
        arr[end].append([start, cost])
    for start in gates:
        visit[start] = 0
        tracking(start, 0)
    return answer
