from heapq import heappop, heappush
heap = [0]
for x in sorted([list(map(int,input().split())) for _ in range(int(input()))], key=lambda x:(x[1], x[2])):
    heappop(heap) if heap[0] <= x[1] else None
    heappush(heap, x[2])
print(len(heap))