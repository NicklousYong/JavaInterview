1. 基础系统指令

• ls：列出目录内容
-a ：展示所有文件，包含隐藏文件
-l:查看详细信息
-d:查看目录本身属性，而非目录下的文件信息
-r:反向排序
-t:按修改时间降序
• cd：切换目录
cd - 切换到上次的目录
• pwd：显示当前工作目录
• mkdir：创建新目录
-p ：创建多级目录
mkdir -p parent/child/grandchild  # 一次性创建嵌套目录
• rmdir：删除空目录
-p：递归删除
rmdir -p a/b/c  # 若c、b、a依次为空，会逐个删除
• touch：创建空文件或更新时间戳
执行touch 之后，如果文件不存在，会创建文件，如果文件存在，会更新时间戳。
• rm：删除文件或目录
- m （modify)更新修改时间  -a （access）更新访问时间
# 将 file.txt 的时间戳改为 2023年10月1日 08:30:00
touch -t 202310010830 file.txt
• cp：复制文件或目录
复制多个文件到目录：cp file1.txt file2.md /home/user/downloads/  # 同时复制 file1.txt 和 file2.md 到 downloads 目录
-p :保留原文件的属性，比如权限，时间戳等
• mv：移动或重命名文件
mv file1.jpg file2.png /tmp/images/  # 同时移动两个文件到 images 目录
移动整个文件夹：mv music/ /media/backup/  # 将 music 目录整体移动到 backup 目录下
• cat：查看文件内容（适合短文件，适合内容较多的文件）
# 查看单个文件
cat file.txt
# 同时查看多个文件（内容会依次显示）
cat note1.txt note2.md
合并多个文件：
# 把 file1.txt 和 file2.txt 的内容合并到 combined.txt
cat file1.txt file2.txt > combined.txt

使用 > 合并文件时，若目标文件已存在，会被覆盖；若想追加内容，可用 >>（如 cat file3.txt >> combined.txt，在原有内容后添加）。

显示行号(包括空行)：cat -n script.sh  # 查看脚本并显示每行的编号。
显示行号(不包括空行)：cat -b article.txt  # 只对非空行编号
• more/less：分页查看文件内容
more:仅支持向前翻页，不支持向后翻页
+n：从第n行开始显示
-f：按实际行数统计（忽略自动换行）
+/pattern：从匹配指定字符串的位置开始显示
空格键：向下翻一页
b：向上翻一页
/字符串：向下搜索指定字符串
=：显示当前行号
q：退出浏览
less:
-N：显示行号
+F：实时监控文件变化
+F：实时监控文件变化
空格键：向下翻一页
b：向上翻一页
上下箭头：逐行滚动
g：跳转到文件开头；G：跳转到文件末尾
/关键词：向前搜索
?关键词：向后搜索
&：组合多个搜索条件

• head：查看文件前几行
head -n 5 file.txt
• tail：查看文件后几行
tail -n 20 filename.txt  # 显示最后20行
tail -f /var/log/syslog  # 实时显示日志文件新增内容
tail -n +50 filename.txt  # 从第50行开始显示到文件末尾
查看文件第10到20行 ：head -n 20 filename.txt | tail -n 10

• ln：创建链接文件（硬链接/软链接）
软链接：又叫做符号链接，相当于windows的快捷方式，用于指向另一个目录。
ln -s  /tmp/ceshiwenjian  abc :这个指令创建了一个指向/tmp/ceshiwenjian 的软链接，其中/tmp/ceshiwenjian存不存在并不重要。
硬链接：一个文件的两个名字，每个名字都指向同一个文件，修改一个文件会影响到另一个文件。ls -l列出文件的时候，展示的第二个字段就是硬链接的数量。（linux系统中，我们看到的文件名只是指向了文件的对象，通过文件名操作文件，类似c的指针）

• date：显示或设置系统日期和时间
修改时间：date -s "2025-08-06 15:30:00"

• cal：显示日历

• clear：清屏

• echo：输出文本

2. 文件权限与用户管理

• chmod：修改文件权限

• chown：修改文件所有者

• chgrp：修改文件所属组
chgrp [选项] 新组名 文件名/目录名
chgrp developers file.txt  :  修改目录的所属组
chgrp -R staff data/  : 递归修改目录及其内容的所属组

• useradd：添加用户
创建基本用户：sudo useradd user1
强制创建家目录：sudo useradd -m user2
创建用户 user3，指定家目录为 /data/user3，默认 shell 为 /bin/zsh：sudo useradd -d /data/user3 -s /bin/zsh -m user3

• userdel：删除用户

• passwd：修改用户密码
更改用户密码 sudo passwd username
锁定用户账号 sudo passwd -l username
解除用户账号锁定 sudo passwd -u username

• su/sudo：切换用户或以管理员权限执行

• groups：查看用户所属组
groups root

3. 进程与系统状态

nohub :启动时输该指令，即使终端断开，进程也不会终止
nohup java -jar your_app.jar &

• ps：查看当前进程
ps -e:查询所有进程的简略信息
ps -f:显示当前用户进程的详细信息
ps -u root  # 显示root用户的进程
ps -p 1234,5678  # 查看PID为1234和5678的进程
ps aux  # 经典组合：显示所有用户的所有进程及详细资源信息
ps -p 1234 -f 查询进程编号为1234的启动指令


• top：实时监控系统进程和资源使用
top -H -p <Java进程PID>  # 直接启动并显示指定Java进程的所有线程

• kill/killall：终止进程
kill -9 12345  # 或 kill -SIGKILL 12345，强制终止 PID 12345 的进程
kill -19 12345  # 发送 SIGSTOP 暂停 PID 12345 的进程（进程状态变为 T）

• pkill：按条件终止进程
pkill java  # 终止所有名称含 "java" 的进程，模糊匹配（发送默认信号 15）
pkill -x "java"  # 仅终止进程名完全为 "java" 的进程（不匹配 "javac"、"javascript" 等）

• jobs：查看后台作业

• bg/fg：将作业放入后台/前台运行

• free：查看系统内存使用情况

• df：查看磁盘空间使用情况
df -h 显示磁盘总容量，已用空间，可用空间
df -i 查看inode 使用情况，有时磁盘空间没满但是inode满了也会提示内存不足(inode用于存储系统中的文件节点，inode满了就无法创建文件)

• du：查看目录或文件占用磁盘空间大小
du -sh 查看当前目录总占用空间大小
du -sh /home/用户名 查看用户目录下空间占用大小
du -sh /home/* 列出所有用户目录的总占用大小

• vmstat：查看虚拟内存状态

• ifconfig：查看网络接口信息（替代指令：ip addr）

• ping：测试网络连通性

• traceroute：追踪网络路由

• netstat：查看网络连接状态

• lsof：查看打开的文件和进程关联
根据进程号查询端口占用情况：lsof -p 18011 -a -I
4. 文本处理与搜索

• grep：在文件中搜索指定文本

• fgrep：固定字符串搜索

• find：查找文件或目录

• wc：统计文件行数、字数、字节数

• sort：对文件内容排序

• uniq：去除重复行

• cut：按列提取文本内容

• tr：转换或删除字符

• sed：流文本编辑器（批量处理文本）

• awk：文本处理工具（支持复杂逻辑）

• patch：应用补丁文件

• tar：打包和压缩文件（如tar -czvf压缩，tar -xzvf解压）
tar [选项] [压缩包文件名] [源文件/目录]  # 压缩
tar [选项] [压缩包文件名] [目标目录]      # 解压
-c:打成新的压缩包
-x:解压缩
-z:使用gzip压缩算法(对应后缀为tar.gz或tgz)
-j:使用bz2算法（后缀为bz2）
-C 目录：（这里C是大写）解压到目录下（需要目录已存在）

tar -jxvf archive.tar.bz2 -C target_dir

• zip/unzip：压缩/解压zip文件

• gzip/gunzip：压缩/解压gzip文件

• bzip2/bunzip2：压缩/解压bzip2文件

5. 软件管理与系统服务

• apt/apt-get：Debian系软件包管理（如apt install安装）

• yum/dnf：RedHat系软件包管理

• rpm：RedHat系软件包管理工具

• dpkg：Debian系软件包管理工具

• make：项目构建工具（配合Makefile）

• configure：软件配置脚本

• service：管理系统服务（如service httpd start）

• systemctl：systemd服务管理（如systemctl enable开机自启）

• chkconfig：传统服务启动级别管理

• reboot：重启系统

• shutdown：关机或重启

• halt/poweroff：关机

6. 网络与远程操作

• ssh：远程登录到其他主机

• scp：安全复制文件到远程主机

• sftp：安全文件传输

• ftp：文件传输协议（非加密）

• telnet：远程登录（非加密）

• rsync：高效同步文件和目录

• curl：网络请求工具（获取网页、API等）

• wget：下载文件到本地

7. 磁盘与分区管理

• fdisk：磁盘分区管理

• mkfs：格式化磁盘分区

• mount：挂载文件系统

• umount：卸载文件系统

• fsck：检查和修复文件系统错误

8. Shell编程与环境

• bash：默认Shell解释器

• source/.：执行Shell脚本并应用环境变量

• export：设置环境变量

• env：查看环境变量

• set：查看Shell变量和函数

• alias：创建命令别名

• history：查看命令历史

• exit：退出Shell会话

你可以直接复制以上内容保存为txt文件，如需进一步分类或补充说明，可以随时告诉我哦~