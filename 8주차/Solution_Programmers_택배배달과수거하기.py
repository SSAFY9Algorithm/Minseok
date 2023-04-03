def solution(cap, n, deliveries, pickups):
    answer = 0

    deli = 0
    pick = 0
    for i in reversed(range(n)):
        deli += deliveries[i]
        pick += pickups[i]
        while deli > 0 or pick > 0:
            deli -= cap
            pick -= cap
            answer += (i + 1) * 2

    return answer