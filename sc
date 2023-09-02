-- Initialize variables
local isSpamming = false
local spamMessage = "" -- Initialize the spam message
local spamDelay = 0.1 -- Default spam delay in seconds

-- Function to start spamming
local function startSpam()
    isSpamming = true
    while isSpamming do
        game.Players:Chat(spamMessage) -- Send the spam message using game.Players.LocalPlayer:Chat
        wait(spamDelay) -- Wait for the specified delay before sending the next message
    end
end

-- Function to stop spamming
local function stopSpam()
    isSpamming = false
end

-- Listen for player chat messages
game.Players.LocalPlayer.Chatted:Connect(function(message)
    local lowerMsg = message:lower()

    if lowerMsg:sub(1, 8) == ".setspam" then
        local newDelay = tonumber(lowerMsg:sub(10)) -- Extract the new spam delay after ".setspam "
        
        if newDelay and newDelay >= 0.00001 then
            spamDelay = newDelay -- Set the new spam delay
        end
    elseif lowerMsg:sub(1, 5) == ".spam" then
        spamMessage = lowerMsg:sub(7) -- Extract the message after ".spam "
        startSpam() -- Start spamming when ".spam" is sent
    elseif lowerMsg == ".stop" then
        stopSpam() -- Stop spamming directly when ".stop" is sent
    end
end)
