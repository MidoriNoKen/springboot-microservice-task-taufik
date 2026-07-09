try {
    $c = Get-Content docs/postman_collection.json -Raw | ConvertFrom-Json
    Write-Host ("collection OK, items=" + $c.item.Count)
} catch {
    Write-Host ("collection ERR: " + $_.Exception.Message)
}
try {
    $e = Get-Content docs/postman_environment.json -Raw | ConvertFrom-Json
    Write-Host ("environment OK, vars=" + $e.values.Count)
} catch {
    Write-Host ("environment ERR: " + $_.Exception.Message)
}