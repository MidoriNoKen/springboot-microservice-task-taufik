$base = "http://localhost:8080/api/books"
$b = @{title='The Pragmatic Programmer'; author='Andrew Hunt & David Thomas'; isbn='978-0-14-044926-6'; publishedDate='1999-10-30'} | ConvertTo-Json
$r = Invoke-RestMethod -Uri $base -Method POST -ContentType 'application/json' -Body $b
$id = $r.id
Write-Host ("POST -> id=" + $id + " title=" + $r.title)
$all = Invoke-RestMethod $base
Write-Host ("GET all -> " + $all.Count + " books")
$g = Invoke-RestMethod ($base + "/" + $id)
Write-Host ("GET id -> " + $g.title)
$u = @{title='Clean Code'; author='Robert C. Martin'; isbn='978-0-13-235088-4'; publishedDate='2008-08-01'} | ConvertTo-Json
$pu = Invoke-RestMethod -Uri ($base + "/" + $id) -Method PUT -ContentType 'application/json' -Body $u
Write-Host ("PUT -> " + $pu.title)
$p = @{title='Patched Title'} | ConvertTo-Json
$pp = Invoke-RestMethod -Uri ($base + "/" + $id) -Method PATCH -ContentType 'application/json' -Body $p
Write-Host ("PATCH -> " + $pp.title)
try { Invoke-RestMethod ($base + "/99999") -Method GET } catch { Write-Host ("GET 99999 -> " + $_.Exception.Response.StatusCode.value__) }
Invoke-RestMethod -Uri ($base + "/" + $id) -Method DELETE | Out-Null
Write-Host ("DELETE -> done")
try { Invoke-RestMethod ($base + "/" + $id) -Method GET } catch { Write-Host ("GET deleted -> " + $_.Exception.Response.StatusCode.value__) }