local key1 = KEYS[1]//字符串1
local key2 = KEYS[2]//字符串2

local Amount = tonumber(ARGV[1])

local is_exists1 = redis.call("EXISTS", key1)
local is_exists2 = redis.call("EXISTS", key2)

if is_exists1 == 1 and is_exists2 == 1 then

    redis.call("DecrBy", key1, Amount)
    redis.call("Set", key2, Amount)
    return 0
else
    return 1
end