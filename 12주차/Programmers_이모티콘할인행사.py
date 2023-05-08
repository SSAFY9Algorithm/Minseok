# 1. 가입자 증가
# 2. 판매액 증가

# 할인 비율이 일정 기준 이상 시 구매
# 구매 비용 합이 일정 기준 이상 시 플러스
answer = [0, 0]
maxRegist = 0


def solution(users, emoticons):
    def bf(idx, buy):
        global answer, maxRegist
        if idx == len(emoticons):
            # print(buy)
            regist = 0
            money = 0
            for i, v in enumerate(users):
                if v[1] <= buy[i]:
                    regist += 1
                else:
                    money += buy[i]
            if regist > maxRegist:
                answer = [regist, money]
                maxRegist = regist
            elif regist == maxRegist:
                answer[1] = max(answer[1], money)
            return
        price = emoticons[idx]
        for per in range(10, 50, 10):
            temp = buy[:]
            for i, v in enumerate(users):
                if v[0] <= per:
                    temp[i] += price * (100 - per) // 100
            bf(idx + 1, temp)

    bf(0, [0] * len(users))

    return answer