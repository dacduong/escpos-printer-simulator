<html>
<head>
    <title>Printer Page</title>
<style>
    .container {
        width: 100%;
    }
    .device {
        border:1px solid;
        width:400px;
        height:600px;
        float: left;
    }
    .device-content {
        max-height:520px;  
        background-color: #EBEBEB;
        overflow:auto;
    }
</style>
</head>
<body>
<div class="container">
<?php
$files = $_GET['files'];
$file_arr = explode(',', $files);
foreach ($file_arr as $filename) {
    $file = getcwd().'/tmp/'.$filename;
    $content = @file_get_contents($file);
    if ($content === false) {
        echo "<div class='device'>File $filename: not found</div>";
        continue;
    }
    $content = nl2br($content);
    echo "<div class='device'><p class='title'>$filename</p><div class='device-content'>$content</div></div>";
}
?>
</div>
<script>
    var x = document.getElementsByClassName("device-content");
    for (var i = 0; i < x.length; i++) {
        x[i].scrollTop = x[i].scrollHeight;
    }
</script>
</body>
</html>
