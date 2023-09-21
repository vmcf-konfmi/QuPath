def channelNames = getCurrentServer().getMetadata().getChannels().collect { c -> c.name }
println channelNames